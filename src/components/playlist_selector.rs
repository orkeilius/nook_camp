use dioxus::prelude::*;

#[component]
pub fn PlaylistSelector() -> Element {
    rsx! {
        select {
            option { value: "music-1", "music 1" }
            option { value: "music-2", "music 2" }
        }
    }
}