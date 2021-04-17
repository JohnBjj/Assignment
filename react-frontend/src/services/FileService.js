import axios from 'axios';

const FILE_API_BASE_URL = "http://localhost:8090/api/files";

class FileService {

    getFiles(){
        return axios.get(FILE_API_BASE_URL);
    }

    uploadFile(document){

        return axios.post(FILE_API_BASE_URL, document);
    }

    getFileById(documentId){
        return axios.get(FILE_API_BASE_URL + '/' + documentId);
    }

    updateFile(document, documentId){
        console.log(documentId);
        return axios.put(FILE_API_BASE_URL + '/' + documentId, document);
    }

    deleteFile(documentId){
        return axios.delete(FILE_API_BASE_URL + '/' + documentId);
    }
}

export default new FileService()