use crate::data::icon_type::IconType;
use dioxus::prelude::*;

#[derive(Props, PartialEq, Clone)]
pub struct IconProps {
    icon: IconType,
    color: String,
    #[props(default)]
    class: String,
}
#[component]
pub fn Icon(props: IconProps) -> Element {
    rsx! {
        if props.icon.is_pixel_art() {
            svg{
                class: props.class,
                view_box: "0 0 16 16",
                shape_rendering: "crispEdges",
                path{
                    stroke: props.color,
                    d: props.icon.path(),
                }
            }
        } else {
            svg{
                class: props.class,
                view_box: "0 0 24 24",
                path {
                    fill: props.color,
                    fill_rule: "evenodd",
                    d: props.icon.path(),
                }
            }
        }
    }
}
