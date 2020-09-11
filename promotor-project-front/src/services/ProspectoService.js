import axios from 'axios';

export default class ProspectoService{
    url= "http://localhost:8080/api/prospects/v1/"

    getAll(){
        return axios.get(this.url +"all")
    }
    
    save(prospecto){
        return axios.post(this.url + "save", prospecto)
    }
    getProspecto(id){
        return axios.get(this.url+"find/"+id)
    }

}