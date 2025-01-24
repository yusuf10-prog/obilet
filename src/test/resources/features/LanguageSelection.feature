@obilet @language
Feature: Dil Seçimi Fonksiyonalitesi

  @smoke @regression
  Scenario: Dil seçiminin İngilizce olarak değiştirilmesi
    Given kullanıcı obilet.com anasayfasına gider
    When kullanıcı dil seçim butonuna tıklar
    And kullanıcı İngilizce seçeneğini seçer
    Then dilin İngilizce olarak değiştiği doğrulanır
