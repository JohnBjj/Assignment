import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import FileService from "../services/FileService";

class LoadFileComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            documents: [],
            file: '',

        }

        this.loadDocument = this.loadDocument.bind(this);


    }

    onFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        });
    }

    loadDocument(e) {
        e.preventDefault()
        const formData = new FormData();
        formData.append('file', this.state.file);
        FileService.uploadFile(formData)
            .then(res => {
                alert("File uploaded successfully.")
                setTimeout(()=>this.props.history.push(`/files`), 1000);


            })


    }

    render() {
        return (

            <div className="container">
                <div className="row">

                    <div className="col-md-8">
                        <input className="form-control form-control-lg"  type="file"  onChange={this.onFileChange}/>
                    </div>
                    <div className="col-md-4">
                        <button className="form-control form-control-lg" disabled={!this.state.file} onClick={this.loadDocument}>Upload File</button>
                    </div>

                </div>
            </div>

        );
    }
}

export default withRouter(LoadFileComponent);