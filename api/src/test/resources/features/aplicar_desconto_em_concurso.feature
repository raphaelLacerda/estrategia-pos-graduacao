Feature: Aplicar desconto em concurso
  Deve calcular desconto de concurso a partir da data de lançamento

  Scenario: deve gerar desconto de 70 porcento para concursos em época de black friday
    Given  dado que concurso com data "2023-11-16" e preco "100.00"
    When quando desconto de BlackFriday for aplicado
    Then entao o concurso deve ter seu valor de "30.00"

  Scenario Outline: Quando concurso não for em época de black friday, deve gerar desconto apenas de 5%
    Given dado que concurso com data "<data>" e preco "<preco>"
    When quando desconto de BlackFriday for aplicado
    Then entao o concurso deve ter seu valor de "<valor>"
  Examples:
    | data                | preco |     valor |
    | 2023-10-15          | 100   |     95.00 |
    | 2023-09-15          | 50    |     47.50 |
    | 2023-08-15          | 10    |     9.50  |

