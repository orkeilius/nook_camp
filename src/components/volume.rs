use crate::components::icon::Icon;
use crate::data::volume_type::VolumeType;
use crate::data::volume_unit::VolumeUnit;
use dioxus::html::input_data::MouseButton;
use dioxus::prelude::*;
use std::rc::Rc;

#[css_module("/assets/styles/volume.css")]
struct Styles;

#[derive(Props, PartialEq, Clone)]
pub struct VolumeProps {
    volume_type: VolumeType,
}

#[component]
pub fn Volume(props: VolumeProps) -> Element {
    let volume_type = props.volume_type;

    let volume_level = use_signal(|| VolumeUnit::new(0.7));
    let mut volume_div = use_signal(|| None);

    rsx! {
        div {
            class: Styles::container,
            div{
                class : Styles::volume,
                onmounted: move |e| {
                    volume_div.set(
                        Some(e.data())
                    );
                },
                onwheel: move |e| handle_on_wheel(e,volume_level),
                onmousedown: move |e| handle_on_mouse(e,volume_level,volume_div),
                onmousemove: move |e| handle_on_mouse(e,volume_level,volume_div),
                div{
                    class: Styles::volume_inner,
                    background: volume_type.color(),
                    height: format!("{}%", volume_level.read().percent() * 100.0)
                },
            },
            Icon{
                class: Styles::icon,
                icon: volume_type.icon(),
                color: if volume_level.read().percent() == 0.0 { "#888888" } else { volume_type.color() },
            }
        }
    }
}

async fn handle_on_mouse(
    event: Event<MouseData>,
    mut volume_level: Signal<VolumeUnit>,
    volume_div: Signal<Option<Rc<MountedData>>>,
) {
    if !event.data().held_buttons().contains(MouseButton::Primary) || volume_div.as_ref().is_none()
    {
        return;
    }
    let client_rect = volume_div.unwrap().get_client_rect().await.unwrap();
    let div_offset = client_rect.origin.y;
    let div_height = client_rect.size.height;

    let pos_relative_target_y = (event.data().client_coordinates().y - div_offset) / div_height;

    volume_level
        .write()
        .set_percent(1.0 - pos_relative_target_y as f32);
}

fn handle_on_wheel(event: Event<WheelData>, mut volume_level: Signal<VolumeUnit>) {
    let direction = event.delta().strip_units().y;
    if direction < 0.0 {
        volume_level.write().volume_up()
    } else {
        volume_level.write().volume_down();
    }
}
