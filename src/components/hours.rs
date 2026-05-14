use chrono::{DateTime, Local, Timelike};
use dioxus::hooks::use_signal;
use dioxus::prelude::*;

#[component]
pub fn Hours() -> Element {
    let current_time: Signal<DateTime<Local>> = use_signal(Local::now);

    use_hook(move || {
        spawn(time_observer(current_time));
    });

    let text = current_time.read().format("%H:%M").to_string();
    rsx! {
        "{text}"
    }
}

async fn time_observer(mut signal: Signal<DateTime<Local>>) {
    loop {
        let duration = time_to_next_minute();

        #[cfg(feature = "web")]
        gloo_timers::future::TimeoutFuture::new(duration.as_millis() as u32).await;
        #[cfg(feature = "desktop")]
        tokio::time::sleep(duration).await;

        signal.set(Local::now());
    }
}
fn time_to_next_minute() -> std::time::Duration {
    let seconds_to_next_minute = 60 - Local::now().second();
    std::time::Duration::from_secs(seconds_to_next_minute as u64)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn renders_current_hour() {
        let pre_hour = Local::now();
        let result = dioxus_ssr::render_element(rsx! { Hours {} });
        let post_hour = Local::now(); // add another check when test is run while minute change

        assert!(
            result.contains(&pre_hour.format("%H:%M").to_string())
                || result.contains(&post_hour.format("%H:%M").to_string())
        );
    }

    #[test]
    fn test_time_to_next_minute() {
        let seconds_before = Local::now().second();
        let result = time_to_next_minute();

        // Account for test execution time
        let expected_min = (60 - seconds_before - 1) as u64;
        let expected_max = (60 - seconds_before + 1) as u64;

        assert!(result.as_secs() >= expected_min && result.as_secs() <= expected_max,);
    }
}
