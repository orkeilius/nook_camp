use dioxus::logger::tracing;
use dioxus::prelude::*;
use crate::components::hours::Hours;

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
                SettingIcon {}
                TownMusicIcon {}
            }
        }
    }
}

#[component]
fn SettingIcon() -> Element{
    rsx!{
        img {
            alt: "Setting Icon",
            class: Styles::icon,
            src: asset!("/assets/icon/heroicons_cog-6-tooth.svg"),
            onclick : move |_| tracing::info!("Todo !")
        }
    }
}

#[component]
fn TownMusicIcon() -> Element{
    rsx!{
        img {
            alt: "Town Music Icon",
            class: Styles::icon,
            src: asset!("/assets/icon/heroicons_bell.svg"),
            onclick : move |_| tracing::info!("Todo !")
        }
    }
}