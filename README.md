## Acme

Para rodar o programa é necessário apenas rodas o jar, os arquivos de configuração estão dentro do mesmo.

Para gerar o jar

* mvn clean package

Executando o jar

* java -jar target/acme-1.0.0.jar 

---

Abaixo alguns pontos que fiquei em dúvida

### Incrementar tempo de intervalo não feito
> Todo tempo de intervalo não gozado é contado como tempo trabalhado ou hora extra desde que o colaborador tenha feito ao menos 50% da jornada do dia

Estou considerando que caso funcionário tenha feito mais de 50% ele **ganhe** esse tempo a mais.

---

Exemplo:

Se um funcionário que tem uma jornada de 8h diárias e um intervalo de 1h com as entradas abaixo teria um balanço de 1h (8:30 trabalhadas + 0:30 do intervalo não feito - 8h da jornada) 

- 10:00
- 13:00
- 13:30
- 19:00

### Funcionário com 3 entradas ou mais de 4 no mesmo dia

O sistema não contabiliza esses dias, entregando o balanço do dia zerado e printando no terminal o funcionário o dia e as entradas registradas

### Trabalhou em dia de folga

Caso seja encontrado registros válidos em um dia que o funcionário não deveria trabalhar o sistema registra todas as horas trabalhadas no balanço como horas extras.
Usando o exemplo acima o balanço seria de 9hs

### Não há registros de entrada

Caso não seja dia de folga(nesses casos é retornado 0 no balanço) o balanço é o total da jornada de trabalho negativo.

Ex: um funcionário que deveria ter uma jornada de 8hs fica com -8hs nesse dia

### Dias utilizados para gerar o relatório

Os campos _today_ e _period_start_ do arquivo _config.json_ são utilizados pelo sistema para calcular o resumo do balanço, indo do _period_start_ até um dia antes do _today_