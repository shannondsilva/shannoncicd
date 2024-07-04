@MvnFirstFeature
Feature: To test my DBB framework

  @TC_1
  Scenario Outline: Testcase 1
    Given User launches browser "<browser>"
    When User enters "<URL>" into the browser and lands on the page
    Then User check for the home and books field

    Examples:
      | browser | URL     |
      | browser | TestURL |


  @TC_2
  Scenario Outline: Testcase 2
    Given User launches browser "<browser>"
    When User enters "<URL>" into the browser and lands on the page
    Then User check for the home and books field

    Examples:
      | browser | URL     |
      | browser | TestURL |


  @TC_3
  Scenario Outline: Testcase 3
    Given User launches browser "<browser>"
    When User enters "<URL>" into the browser and lands on the page
    Then User check for the home and books field

    Examples:
      | browser | URL     |
      | browser | TestURL |