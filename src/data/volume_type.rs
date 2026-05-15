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
