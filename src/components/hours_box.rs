use crate::components::hours::Hours;
use crate::components::icon::Icon;
use crate::data::icon_type::IconType;
use dioxus::logger::tracing;
use dioxus::prelude::*;

#[css_module("/assets/styles/hours_box.css")]
struct Styles;

#[component]
pub fn HourBox() -> Element {
    rsx! {
        div{
            class: Styles::container,
            p {
                class: Styles::hours,
                Hours{}
            }
            div{
                class: Styles::icon_container,
                div {
                    class: Styles::icon,
                    onclick: move |_| tracing::info!("Todo !"),
                    Icon {
                        class: Styles::icon,
                        icon: IconType::Setting,
                        color: "white".to_string()
                    }
                }
                div {
                    class: Styles::icon,
                    onclick: move |_| tracing::info!("Todo !"),
                    Icon {
                        class: Styles::icon,
                        icon: IconType::TownMusic,
                        color: "white".to_string()
                    }
                }
            }
        }
    }
}
