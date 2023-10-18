Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Today isn't Friday
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<answer>"
  Examples:
    | day            | answer |
    | Saturday       | false   |
    | Sunday         | false   |
    | Monday         | false   |

  Scenario: Today is Friday
    Given today is "Friday"
    When I ask whether it's Friday yet
    Then I should be told "true"