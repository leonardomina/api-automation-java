Feature: Testes da API de Usuários

  Scenario: Buscar todos os usuários GET
    Given que a API de usuários está disponível
    When eu realizo uma requisição GET para "/usuarios"
    Then a resposta deve conter status 200
    And a resposta deve conter uma lista de usuários

  Scenario: Criar um novo usuário POST e validar com GET
    Given que a API de usuários está disponível
    When eu realizo uma requisição POST para "/usuarios" com os seguintes dados:
      | nome         | email        | password | administrador |
      | Novo Usuário | email_123456 | 123456   | true          |
    Then a resposta deve conter status 201
    And a resposta deve conter a mensagem "Cadastro realizado com sucesso"
    When eu realizo uma requisição GET para "/usuarios/{id}"
    Then a resposta deve conter status 200

  Scenario: Atualizar um usuário existente PUT
    Given que um usuário com id "id_aqui" existe
    When eu realizo uma requisição PUT para "/usuarios/id_aqui" com os seguintes dados:
      | nome               |
      | Usuário Atualizado |
    Then a resposta deve conter status 200
    And a resposta deve conter a mensagem "Registro alterado com sucesso"

  Scenario: Deletar um usuário existente DELETE e validar remoção
    Given que um usuário com id "{id}" existe
    When eu realizo uma requisição DELETE para "/usuarios/{id}"
    Then a resposta deve conter status 200
    And a resposta deve conter a mensagem "Registro excluído com sucesso"
    When eu realizo uma requisição GET para "/usuarios/{id}"
    Then a resposta deve conter status 400