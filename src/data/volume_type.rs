use crate::data::icon_type::IconType;

#[derive(Clone, PartialEq)]
pub enum VolumeType {
    Music,
    Rain,
}

impl VolumeType {
    pub fn color(&self) -> &'static str {
        match self {
            VolumeType::Music => "#8959d6",
            VolumeType::Rain => "#1ab3dd",
        }
    }
    pub fn icon(&self) -> IconType {
        match self {
            VolumeType::Music => IconType::Music,
            VolumeType::Rain => IconType::Rain,
        }
    }
}

#[cfg(test)]
mod volume_type_test {
    use super::*;
    use parameterized::parameterized;

    #[parameterized(
        input = {VolumeType::Music, VolumeType::Rain},
        expected = {"#8959d6", "#1ab3dd"},
    )]
    fn color_test(input: VolumeType, expected: &str) {
        assert_eq!(input.color(), expected);
    }

    #[parameterized(
        input = {VolumeType::Music, VolumeType::Rain},
        expected = {IconType::Music, IconType::Rain},
    )]
    fn icon_test(input: VolumeType, expected: IconType) {
        assert_eq!(input.icon(), expected);
    }
}
