import axios from 'axios';

function nameValidator(name) {
    return (axios.get('http://localhost:8080/getNames').then(response=>{
        let names = [...response.data];
        if(names.includes(name)){
          return false;
        }
        else{
          return true;
        }
    }));
}
  
export default nameValidator;