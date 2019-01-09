# smileAuthorizaiton
smilegate personal assignment2 - departing apiserver and authserver

#### pathvariable option 설정
  ```java
  @GetMapping(path = {"/{userId}", ""})
  ResponseEntity getUser(@PathVariable Optional<Integer> userId){
      if(userId.isPresent()){ 
            
      }else{
            
      }
  }
  ```
  
#### mySQL timestap 설정
| ColumnName | DataType | Default/Expression |
| ---------- | -------- | ------------------ |
| createTime | TIMESTAMP | CURRENT_TIMESTAMP                             |
| updateTime | TIMESTAMP | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |
