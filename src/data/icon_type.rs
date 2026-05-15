#[derive(Clone, PartialEq, Debug)]
pub enum IconType {
    Music,
    Rain,
    Setting,
    TownMusic,
}

impl IconType {
    pub fn path(&self) -> &'static str {
        match self {
            IconType::Music => "M4 0h2M4 1h4M4 2h6M4 3h8M4 4h10M4 5h2M8 5h6M4 6h2M10 6h4M4 7h2M12 7h2M1 8h5M12 8h2M0 9h6M12 9h2M0 10h4M5 10h1M12 10h2M0 11h3M5 11h1M9 11h5M0 12h2M4 12h2M8 12h4M13 12h1M1 13h4M8 13h3M13 13h1M8 14h2M12 14h2M9 15h4",
            IconType::Rain => "M7 0h2M6 1h4M5 2h6M4 3h3M9 3h3M3 4h3M10 4h3M3 5h2M11 5h2M2 6h2M6 6h2M12 6h2M2 7h2M5 7h4M12 7h2M1 8h2M5 8h4M13 8h2M1 9h2M6 9h2M13 9h2M1 10h2M13 10h2M1 11h2M13 11h2M2 12h2M12 12h2M2 13h4M10 13h4M3 14h10M5 15h6",
            IconType::Setting => "M11.078 2.25c-.917 0-1.699.663-1.85 1.567L9.05 4.889c-.02.12-.115.26-.297.348a7.493 7.493 0 0 0-.986.57c-.166.115-.334.126-.45.083L6.3 5.508a1.875 1.875 0 0 0-2.282.819l-.922 1.597a1.875 1.875 0 0 0 .432 2.385l.84.692c.095.078.17.229.154.43a7.598 7.598 0 0 0 0 1.139c.015.2-.059.352-.153.43l-.841.692a1.875 1.875 0 0 0-.432 2.385l.922 1.597a1.875 1.875 0 0 0 2.282.818l1.019-.382c.115-.043.283-.031.45.082.312.214.641.405.985.57.182.088.277.228.297.35l.178 1.071c.151.904.933 1.567 1.85 1.567h1.844c.916 0 1.699-.663 1.85-1.567l.178-1.072c.02-.12.114-.26.297-.349.344-.165.673-.356.985-.57.167-.114.335-.125.45-.082l1.02.382a1.875 1.875 0 0 0 2.28-.819l.923-1.597a1.875 1.875 0 0 0-.432-2.385l-.84-.692c-.095-.078-.17-.229-.154-.43a7.614 7.614 0 0 0 0-1.139c-.016-.2.059-.352.153-.43l.84-.692c.708-.582.891-1.59.433-2.385l-.922-1.597a1.875 1.875 0 0 0-2.282-.818l-1.02.382c-.114.043-.282.031-.449-.083a7.49 7.49 0 0 0-.985-.57c-.183-.087-.277-.227-.297-.348l-.179-1.072a1.875 1.875 0 0 0-1.85-1.567h-1.843ZM12 15.75a3.75 3.75 0 1 0 0-7.5 3.75 3.75 0 0 0 0 7.5Z",
            IconType::TownMusic => "M5.25 9a6.75 6.75 0 0 1 13.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 0 1-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 1 1-7.48 0 24.585 24.585 0 0 1-4.831-1.244.75.75 0 0 1-.298-1.205A8.217 8.217 0 0 0 5.25 9.75V9Zm4.502 8.9a2.25 2.25 0 1 0 4.496 0 25.057 25.057 0 0 1-4.496 0Z",
        }
    }
    pub fn is_pixel_art(&self) -> bool {
        match self {
            IconType::Music | IconType::Rain => true,
            IconType::Setting | IconType::TownMusic => false,
        }
    }
}

#[cfg(test)]
mod icon_type_test {
    use super::*;
    use parameterized::parameterized;

    #[parameterized(
        input = {IconType::Music, IconType::Rain, IconType::Setting, IconType::TownMusic},
        expected = {true,true,false,false}
    )]
    fn is_pixel_art_test(input: IconType, expected: bool) {
        assert_eq!(expected, input.is_pixel_art());
    }

    #[parameterized(
        input = {IconType::Music, IconType::Rain, IconType::Setting, IconType::TownMusic},
        expected = {211,207,1060,287}
    )]
    fn path_test(input: IconType, expected: usize) {
        assert_eq!(input.path().len(), expected);
    }
}
