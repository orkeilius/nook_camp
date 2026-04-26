use chrono::{DateTime, Local, Timelike};
use dioxus::hooks::{use_effect, use_signal};
use dioxus::prelude::*;
use tokio::time::sleep;

#[component]
pub fn Hours() -> Element {
    let current_time: Signal<DateTime<Local>> = use_signal(|| Local::now());

    use_effect(move || {
        spawn(time_observer(current_time));
    });

    let text = current_time.read().format("%H:%M").to_string();
    rsx! {
        "{text}"
    }
}

async fn time_observer(mut signal: Signal<DateTime<Local>>) {
    loop {
        sleep(time_to_next_minute()).await;
        signal.set(Local::now());
    }
}
fn time_to_next_minute() -> std::time::Duration {
    let seconds_to_next_minute = 60 - Local::now().second();
    std::time::Duration::from_secs(seconds_to_next_minute as u64)
}
