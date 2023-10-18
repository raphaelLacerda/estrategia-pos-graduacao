Feature: Cadastro de disciplina no sistema
  O sistema deve cadastrar a disciplina e associar professor

  Scenario Outline: Deve cadastrar disciplina com professor até 1 disciplina
    Given dado que disciplina foi preenchida "itil"
    When quando for associado professor que tem "<total>" disciplinas
    Then entao disciplina deve ser cadastrada com sucesso
    Examples:
      | total     |
      | 0         |
      | 1         |

  Scenario: Não deve cadastrar disciplina com professor que já possui mais de 2 disciplinas
    Given dado que disciplina foi preenchida "itil"
    When quando for associado professor que tem "2" disciplinas
    Then entao disciplina nao deve ser cadastrada