# 🏆 Projeto de Automação de Testes de API - Java + Rest Assured + Cucumber

Este repositório contém um projeto de **testes automatizados de API** utilizando **Java, Rest Assured, Cucumber, JUnit e Swagger**. O objetivo é validar endpoints de uma API REST seguindo boas práticas de automação e gerando relatórios detalhados.

---

## 📌 **1. Pré-requisitos**
Antes de começar, certifique-se de ter os seguintes programas instalados:

### 🖥️ **Softwares necessários**
| Programa         | Versão Requerida | Link de Download |
|-----------------|----------------|------------------|
| **Java JDK**    | 11+            | [Baixar JDK](https://adoptium.net/) |
| **IntelliJ IDEA** | Última versão (Community ou Ultimate) | [Baixar IntelliJ IDEA](https://www.jetbrains.com/idea/download/) |
| **Maven**       | 3.8+           | [Baixar Maven](https://maven.apache.org/download.cgi) |
| **Git**         | Última versão  | [Baixar Git](https://git-scm.com/downloads) |

Para verificar as versões instaladas, execute:
```sh
java -version
mvn -version
git --version
```

---

## 📌 **2. Configuração do Ambiente**
### 🔹 **2.1. Instalar e Configurar o Java**
1. Baixe e instale o **JDK 11 ou superior**.
2. Configure a variável de ambiente `JAVA_HOME`:
   - No Windows:
     ```
     JAVA_HOME=C:\Program Files\Java\jdk-11
     PATH=%JAVA_HOME%\bin;%PATH%
     ```
   - No Linux/Mac:
     ```sh
     export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11/Contents/Home
     export PATH=$JAVA_HOME/bin:$PATH
     ```
3. Teste a instalação:
   ```sh
   java -version
   ```

### 🔹 **2.2. Instalar e Configurar o Maven**
1. Baixe e extraia o Maven.
2. Configure a variável de ambiente `MAVEN_HOME` e adicione `bin` ao `PATH`.
   ```sh
   MAVEN_HOME=C:\apache-maven-3.8.6
   PATH=%MAVEN_HOME%\bin;%PATH%
   ```
3. Teste a instalação:
   ```sh
   mvn -version
   ```

### 🔹 **2.3. Clonar o Projeto**
```sh
git clone <URL_DO_REPOSITORIO>
cd api-automation-java
```

---

## 📌 **3. Estrutura do Projeto**
📂 api-automation-java/
├── 📂 src
│   ├── 📂 main
│   │   ├── 📂 java (se necessário, classes de apoio)
│   ├── 📂 test
│   │   ├── 📂 java
│   │   │   ├── 📂 runners          # Arquivo para executar os testes
│   │   │   ├── 📂 steps            # Step Definitions
│   │   │   ├── 📂 requests         # Classes para chamadas de API
│   │   │   ├── 📂 models           # Representação de objetos (POJOs)
│   │   │   ├── 📂 utils            # Métodos auxiliares
│   │   ├── 📂 resources
│   │   │   ├── 📂 features         # Arquivos .feature do Cucumber
├── 📜 pom.xml                      # Configuração do Maven
├── 📜 README.md                     # Documentação

---

## 📌 **4. Configuração no IntelliJ IDEA**
1. **Abrir o Projeto:**
   - Abra o IntelliJ IDEA → Clique em **Open** → Selecione a pasta do projeto.
2. **Configurar o Maven no IntelliJ:**
   - Vá em **File → Settings → Build, Execution, Deployment → Maven**
   - Em "Maven home directory", selecione o caminho do Maven instalado.
3. **Instalar Plugins (Opcional):**
   - Cucumber for Java
   - Gherkin

---

## 📌 **5. Executando os Testes**
### 🔹 **Via Terminal**
```sh
mvn clean test
```

### 🔹 **Executando no IntelliJ**
1. Vá até a classe `Runner.java`.
2. Clique com o botão direito e selecione **Run 'Runner'**.

---

## 📌 **6. Geração de Relatórios**
Após a execução dos testes, os relatórios são gerados em:
```sh
target/cucumber-reports/
```
Para visualizar, abra `index.html` no navegador.

Caso queira gerar um relatório HTML automático:
```sh
mvn clean test -Dcucumber.options="--plugin html:target/cucumber-reports/index.html"
```

---

## 📌 **7. Captura de Evidências (Screenshots)**
Os prints são capturados automaticamente ao final de cada passo do teste e adicionados ao relatório.

Os arquivos de screenshot são salvos em:
```sh
screenshots/
```

---

## 📌 **8. Problemas Comuns e Soluções**
### ❌ Erro: "Maven não reconhecido"
✅ **Solução:** Adicione o Maven ao `PATH` do sistema ou reinstale.

### ❌ Erro: "Java não reconhecido"
✅ **Solução:** Verifique a instalação do Java e configure a variável de ambiente `JAVA_HOME`.

### ❌ Erro: "Cannot find cucumber.json"
✅ **Solução:** Verifique se `json:target/cucumber-reports/cucumber.json` está corretamente configurado no `@CucumberOptions`.

---

## 📌 **9. Contribuição**
1. **Fork** este repositório
2. Crie um **branch** com sua feature:
   ```sh
   git checkout -b minha-feature
   ```
3. Faça o **commit** das mudanças:
   ```sh
   git commit -m "Adicionei uma nova feature"
   ```
4. Faça um **push** para o branch:
   ```sh
   git push origin minha-feature
   ```
5. Abra um **Pull Request**.


