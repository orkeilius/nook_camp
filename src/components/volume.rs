use dioxus::html::geometry::WheelDelta;
use crate::data::volume_type::VolumeType;
use crate::data::VolumeUnit::VolumeUnit;
use dioxus::prelude::*;

#[css_module("/assets/styles/volume.css")]
struct Styles;

#[derive(Props, PartialEq, Clone)]
pub struct VolumeProps {
    volume_type: VolumeType,
}

#[component]
pub fn Volume(props: VolumeProps) -> Element {
    let volume_type = props.volume_type;

    let color = "#2345BD";
    let mut volume_level = use_signal(|| VolumeUnit::new(0.7));

    rsx! {
        div {
            class: Styles::container,
            div{
                class : Styles::volume,
                onwheel: move |e| handleWheelEvent(e,volume_level),
                div{
                    class: Styles::volume_inner,
                    background: color,
                    height: format!("{}%", volume_level.read().percent() * 100.0)
                },



            }
            img{}
        }
    }
}

fn handleWheelEvent(event: Event<WheelData>, mut volume_level: Signal<VolumeUnit>) {
    let direction = event.delta().strip_units().y;
    println!("scroll direction: {} volume: {}", direction,volume_level.read().percent());
    if direction < 0.0 {
        volume_level.write().volume_up()
    }else{
        volume_level.write().volume_down();
    }
}