
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
        self.percent = percent.clamp(0.0, 1.0);
    }

    pub fn volume_up(&mut self) {
        self.set_percent(self.percent + VOLUME_STEP);
    }

    pub fn volume_down(&mut self) {
        self.set_percent(self.percent - VOLUME_STEP);
    }
}

#[test]
fn test_volume_unit() {
    let mut volume = VolumeUnit { percent: 0.5 };

    volume.volume_up();
    assert_eq!(volume.percent(), 0.6);

    volume.volume_down();
    assert_eq!(volume.percent(), 0.5);

    volume.set_percent(1.2);
    assert_eq!(volume.percent(), 1.0);

    volume.set_percent(-0.5);
    assert_eq!(volume.percent(), 0.0);

    volume.set_percent(0.7);
    assert_eq!(volume.percent(), 0.7);
}