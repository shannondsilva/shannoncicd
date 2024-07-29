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


  @ChemicalCode
  Scenario Outline: Find the chemical element count
    Given User needs to find the chemical element count for "<Checmical Formulae>"

    Examples:
      | Checmical Formulae |
      | Mg((OH)2He)4HeNO2       |

  @ChemicalCode1
  Scenario Outline: Find the chemical element count
    Given User needs to find the chemical element count for "<Checmical Formulae>"

    Examples:
      | Checmical Formulae |
      | Mg((OH)2He)4HeNO2       |

  @ChemicalCode2
  Scenario Outline: Find the chemical element count
    Given User needs to find the chemical element count for "<Checmical Formulae>"

    Examples:
      | Checmical Formulae |
      | Mg((OH)2He)4HeNO2       |

  @ChemicalCode3
  Scenario Outline: Find the chemical element count
    Given User needs to find the chemical element count for "<Checmical Formulae>"

    Examples:
      | Checmical Formulae |
      | Mg((OH)2He)4HeNO2       |



  @Pojo
  Scenario Outline: Find the chemical element count
    Given Test my POJO

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |



  @jdbcConnection
  Scenario Outline: JDBC Connection
    Given Connecting to MySQL Using the JDBC DriverManager Interface

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |



  @Duplicates
  Scenario Outline: Find Duplicates in an Array
    Given Find Duplicates in an Array

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |



  @Docker
  Scenario Outline: Docker testing
    Given To test my docker knowledge

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |


  @naukri
  Scenario Outline: Run naukri.com profile
    Given Run my naukri.com profile

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |


  @batFileBasic
  Scenario Outline: BatFile Runs
    Given BAT file executions for docker up

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |


  @DemoTest
  Scenario Outline: BatFile Runs
    Given Demo test program

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |


  @StreamsAndLamda
  Scenario Outline: StreamsAndLamda
    Given Demo test StreamsAndLamda

    Examples:
      | Checmical Formulae |
      | Mg(OH)2            |