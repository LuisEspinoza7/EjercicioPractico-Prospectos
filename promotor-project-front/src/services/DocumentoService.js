import axios from 'axios'

export default class DocumentoService{
    url="http://localhost:8080/api/documents/v1/"
    getAll(){
        return axios.get(this.url+"all")
    }
    save(documento){
        return axios.post(this.url + "save", documento)
    }
}