pub struct VolumeUnit {
    percent: f32,
}

const VOLUME_STEP: f32 = 0.1;

impl VolumeUnit {
    pub fn new(percent: f32) -> Self {
        Self {
            percent: percent.clamp(0.0, 1.0),
        }
    }

    pub fn percent(&self) -> f32 {
        self.percent
    }

    pub fn set_percent(&mut self, percent: f32) {
        let rouded = (percent * 10.0).round() / 10.0;
        self.percent = rouded.clamp(0.0, 1.0);
    }

    pub fn volume_up(&mut self) {
        self.set_percent(self.percent + VOLUME_STEP);
    }

    pub fn volume_down(&mut self) {
        self.set_percent(self.percent - VOLUME_STEP);
    }
}

#[cfg(test)]
mod volume_unit_test {

    use super::*;
    use parameterized::parameterized;

    #[parameterized(
        input = {0.0,0.5,1.0,1.2,-0.5},
        expected = {0.0,0.5,1.0,1.0,0.0},
    )]
    fn new_test(input: f32, expected: f32) {
        let v = VolumeUnit::new(input);
        assert_eq!(v.percent(), expected);
    }

    #[parameterized(
        input = {1.2,-0.5,0.7,0.001,0.99},
        expected = {1.0,0.0,0.7,0.0,1.0},
    )]
    fn set_percent_test(input: f32, expected: f32) {
        let mut volume = VolumeUnit { percent: 0.5 };

        volume.set_percent(input);
        assert_eq!(volume.percent(), expected);
    }

    #[parameterized(
        input = {0.0,0.555,0.95,1.0},
        expected = {0.1,0.7,1.0,1.0},
    )]
    fn volume_up_test(input: f32, expected: f32) {
        let mut v = VolumeUnit::new(input);
        v.volume_up();
        assert_eq!(v.percent(), expected);
    }

    #[parameterized(
        input = {0.0,0.555,0.95,1.0},
        expected = {0.0,0.5,0.9,0.9},
    )]
    fn volume_down_test(input: f32, expected: f32) {
        let mut v = VolumeUnit::new(input);
        v.volume_down();
        assert_eq!(v.percent(), expected);
    }
}
