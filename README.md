# TurboApp - Documentação Técnica

Bem-vindo à documentação do projeto **TurboApp**. Este repositório contém o código-fonte e as instruções necessárias para executar o projeto e entender suas funcionalidades e arquitetura.

## Introdução

O **TurboApp** tem como principal objetivo reduzir o uso excessivo de veículos individuais em São Paulo, aliviando o congestionamento urbano, diminuindo a poluição atmosférica e promovendo a sustentabilidade. O projeto oferece uma alternativa prática e eficiente para a mobilidade urbana, visando melhorar a qualidade de vida dos cidadãos.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação usada para o desenvolvimento do aplicativo Android.
- **Firebase**: Plataforma utilizada para autenticação de usuários, armazenamento em tempo real (Firebase Realtime Database ou Firestore) e hospedagem.
- **Firebase Authentication**: Serviço de autenticação para registro e login de usuários.
- **Firebase Firestore**: Banco de dados NoSQL usado para persistência de dados.
- **Firebase Storage**: Utilizado para armazenar arquivos, como fotos e vídeos.
- **Material Design**: Framework de design para a criação de interfaces visuais consistentes e amigáveis.

## Pré-requisitos

Antes de iniciar a execução do projeto, é necessário garantir que você tenha as seguintes ferramentas instaladas:

- **Android Studio**: [Instalar Android Studio](https://developer.android.com/studio)
- **Kotlin**: Já incluído no Android Studio, ou configurar o ambiente Kotlin.
- **Git**: [Instalar Git](https://git-scm.com/)

Além disso, você precisará de uma conta no Firebase para configurar a autenticação e o banco de dados, assim como uma conta no GitHub para acessar e clonar o repositório.

## Instruções para Execução

### 1. Clonando o Repositório

Para clonar o repositório localmente, execute o seguinte comando no seu terminal:

```bash
git clone https://github.com/ferfavaro/fiapApp.git
cd fiapApp

```

## 2. Configurando Firebase

- Crie um novo projeto no [Firebase Console](https://console.firebase.google.com/).
-  Ative os serviços de **Authentication** e **Firestore** (ou **Realtime Database**).
-  Baixe o arquivo `google-services.json` e coloque-o na pasta `app/` do seu projeto Android.

## 3. Instalando Dependências

As dependências para Kotlin e Firebase serão gerenciadas automaticamente pelo Android Studio com base nas configurações do arquivo `build.gradle`.

## 4. Executando o Projeto

-  Abra o projeto no **Android Studio**.
-  Conecte um dispositivo Android ou inicie um emulador.
-  Clique no botão **Run** para compilar e rodar o aplicativo.

## 5. Configurações Adicionais

- **Variáveis de Ambiente**: Todas as variáveis de ambiente necessárias estão configuradas no arquivo `google-services.json`, que é responsável pela gestão de chaves e acessos.

## Funcionalidades Implementadas

O **TurboApp** inclui as seguintes funcionalidades:

- **Autenticação de Usuário**: Usuários podem se registrar e fazer login usando o Firebase Authentication (suporte para e-mail/senha, etc.).
-  **Gerenciamento de Dados**: Permite a criação, leitura, atualização e exclusão (CRUD) de dados usando Firebase Firestore.
-  **Notificações em Tempo Real**: Alterações nos dados são refletidas automaticamente no aplicativo, graças ao Firebase Realtime Database.
-  **Armazenamento de Arquivos**: Fotos e outros arquivos podem ser enviados e armazenados no Firebase Storage.
-  **Interface Responsiva**: O aplicativo segue os padrões do Material Design, oferecendo uma interface intuitiva e amigável.
-  **Solicitação de Veículos**: O usuário pode solicitar veículos com base em sua disponibilidade e nos filtros escolhidos.
-  **Informações em Tempo Real**: Dados sobre localização e disponibilidade dos veículos são atualizados em tempo real.
-  **Reserva Instantânea**: Os usuários podem reservar veículos rapidamente com base na geolocalização.
-  **Pagamento Integrado**: Pagamentos seguros e integrados, eliminando a necessidade de transações físicas.
-  **Gestão de Devolução de Veículos**: O usuário pode gerenciar a devolução de veículos diretamente pelo aplicativo.

## Arquitetura

O projeto utiliza uma arquitetura baseada em **MVVM** (Model-View-ViewModel):

- **Model (Modelo)**: Responsável pelos dados e a lógica de negócios, integrando-se ao Firebase para gerenciar dados e autenticação.
- **View (Visão)**: Interface do usuário construída com base em Material Design e gerenciada pelos fragments e activities do Android.
- **ViewModel**: Responsável por mediar a comunicação entre a View e o Model, garantindo que os dados sejam atualizados corretamente.

## Estrutura de Diretórios

```plaintext
fiapApp/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Código-fonte do aplicativo Android
│   │   │   ├── res/            # Arquivos de recursos (layouts, strings, etc.)
│   │   └── androidTest/        # Testes de integração para Android
├── google-services.json        # Arquivo de configuração do Firebase
└── README.md                   # Documentação do projeto

```

## Permissões e Acessos

... (15 linhas)
Recolher
message.txt
6 KB
voltei
﻿
# TurboApp - Documentação Técnica

Bem-vindo à documentação do projeto **TurboApp**. Este repositório contém o código-fonte e as instruções necessárias para executar o projeto e entender suas funcionalidades e arquitetura.

## Introdução

O **TurboApp** tem como principal objetivo reduzir o uso excessivo de veículos individuais em São Paulo, aliviando o congestionamento urbano, diminuindo a poluição atmosférica e promovendo a sustentabilidade. O projeto oferece uma alternativa prática e eficiente para a mobilidade urbana, visando melhorar a qualidade de vida dos cidadãos.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação usada para o desenvolvimento do aplicativo Android.
- **Firebase**: Plataforma utilizada para autenticação de usuários, armazenamento em tempo real (Firebase Realtime Database ou Firestore) e hospedagem.
- **Firebase Authentication**: Serviço de autenticação para registro e login de usuários.
- **Firebase Firestore**: Banco de dados NoSQL usado para persistência de dados.
- **Firebase Storage**: Utilizado para armazenar arquivos, como fotos e vídeos.
- **Material Design**: Framework de design para a criação de interfaces visuais consistentes e amigáveis.

## Pré-requisitos

Antes de iniciar a execução do projeto, é necessário garantir que você tenha as seguintes ferramentas instaladas:

- **Android Studio**: [Instalar Android Studio](https://developer.android.com/studio)
- **Kotlin**: Já incluído no Android Studio, ou configurar o ambiente Kotlin.
- **Git**: [Instalar Git](https://git-scm.com/)

Além disso, você precisará de uma conta no Firebase para configurar a autenticação e o banco de dados, assim como uma conta no GitHub para acessar e clonar o repositório.

## Instruções para Execução

### 1. Clonando o Repositório

Para clonar o repositório localmente, execute o seguinte comando no seu terminal:

```bash
git clone https://github.com/ferfavaro/fiapApp.git
cd fiapApp

```

## 2. Configurando Firebase

- Crie um novo projeto no [Firebase Console](https://console.firebase.google.com/).
-  Ative os serviços de **Authentication** e **Firestore** (ou **Realtime Database**).
-  Baixe o arquivo `google-services.json` e coloque-o na pasta `app/` do seu projeto Android.

## 3. Instalando Dependências

As dependências para Kotlin e Firebase serão gerenciadas automaticamente pelo Android Studio com base nas configurações do arquivo `build.gradle`.

## 4. Executando o Projeto

-  Abra o projeto no **Android Studio**.
-  Conecte um dispositivo Android ou inicie um emulador.
-  Clique no botão **Run** para compilar e rodar o aplicativo.

## 5. Configurações Adicionais

- **Variáveis de Ambiente**: Todas as variáveis de ambiente necessárias estão configuradas no arquivo `google-services.json`, que é responsável pela gestão de chaves e acessos.

## Funcionalidades Implementadas

O **TurboApp** inclui as seguintes funcionalidades:

- **Autenticação de Usuário**: Usuários podem se registrar e fazer login usando o Firebase Authentication (suporte para e-mail/senha, etc.).
-  **Gerenciamento de Dados**: Permite a criação, leitura, atualização e exclusão (CRUD) de dados usando Firebase Firestore.
-  **Notificações em Tempo Real**: Alterações nos dados são refletidas automaticamente no aplicativo, graças ao Firebase Realtime Database.
-  **Armazenamento de Arquivos**: Fotos e outros arquivos podem ser enviados e armazenados no Firebase Storage.
-  **Interface Responsiva**: O aplicativo segue os padrões do Material Design, oferecendo uma interface intuitiva e amigável.
-  **Solicitação de Veículos**: O usuário pode solicitar veículos com base em sua disponibilidade e nos filtros escolhidos.
-  **Informações em Tempo Real**: Dados sobre localização e disponibilidade dos veículos são atualizados em tempo real.
-  **Reserva Instantânea**: Os usuários podem reservar veículos rapidamente com base na geolocalização.
-  **Pagamento Integrado**: Pagamentos seguros e integrados, eliminando a necessidade de transações físicas.
-  **Gestão de Devolução de Veículos**: O usuário pode gerenciar a devolução de veículos diretamente pelo aplicativo.

## Arquitetura

O projeto utiliza uma arquitetura baseada em **MVVM** (Model-View-ViewModel):

- **Model (Modelo)**: Responsável pelos dados e a lógica de negócios, integrando-se ao Firebase para gerenciar dados e autenticação.
- **View (Visão)**: Interface do usuário construída com base em Material Design e gerenciada pelos fragments e activities do Android.
- **ViewModel**: Responsável por mediar a comunicação entre a View e o Model, garantindo que os dados sejam atualizados corretamente.

## Estrutura de Diretórios

```plaintext
fiapApp/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Código-fonte do aplicativo Android
│   │   │   ├── res/            # Arquivos de recursos (layouts, strings, etc.)
│   │   └── androidTest/        # Testes de integração para Android
├── google-services.json        # Arquivo de configuração do Firebase
└── README.md                   # Documentação do projeto

```

## Permissões e Acessos

O repositório foi configurado para que o professor tutor tenha acesso ao projeto. O usuário lucasribeirolrm foi adicionado como colaborador com permissões de leitura. O acesso ao repositório pode ser feito pelo link: https://github.com/ferfavaro/fiapApp

## Contato

Caso tenha dúvidas ou precise de mais informações, entre em contato:

- GitHub: ferfavaro
- E-mail: devfavaro@outlook.com
