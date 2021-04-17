import React, { Component } from 'react'
import FileService from "../services/FileService";

class UpdateFileComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            docType: '',
            companyId: '',
            date: '',
            docId: '',
            sign: '',
            amount: '',
            action: ''
        }
        this.changeDocumentTypeHandler = this.changeDocumentTypeHandler.bind(this);
        this.changeCompanyIdHandler = this.changeCompanyIdHandler.bind(this);
        this.changeDateHandler = this.changeDateHandler.bind(this);
        this.changeDocumentIdHandler = this.changeDocumentIdHandler.bind(this);
        this.changeSignHandler = this.changeSignHandler.bind(this);
        this.changeAmountHandler = this.changeAmountHandler.bind(this);
        this.updateDocument = this.updateDocument.bind(this);


    }

    componentDidMount(){
        FileService.getFileById(this.state.id).then( (res) =>{
            let document = res.data;
            this.setState({
                docType: document.docType,
                companyId: document.companyId,
                date : document.date,
                docId : document.docId,
                sign : document.sign,
                amount : document.amount
            });
        });
    }

    updateDocument = (e) => {
        e.preventDefault();
        let document = {
            docType: this.state.docType,
            companyId: this.state.companyId,
            date: this.state.date,
            docId: this.state.docId,
            sign: this.state.sign,
            amount: this.state.amount
        };
        FileService.updateFile(document, this.state.id).then( res => {
            this.props.history.push('/files');
        });
    }
    
    changeDocumentTypeHandler= (event) => {
        this.setState({docType: event.target.value});
    }

    changeCompanyIdHandler= (event) => {
        this.setState({comapnyId: event.target.value});
    }

    changeDateHandler= (event) =>{
        this.setState({date: event.target.value});
    }

    changeDocumentIdHandler= (event) => {
        this.setState({docId: event.target.value});
    }
    changeSignHandler= (event) => {
        this.setState({signn: event.target.value});
    }
    changeAmountHandler= (event) => {
        this.setState({amount: event.target.value});
    }



    cancel(){
        this.props.history.push('/files');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Document</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Document Type: </label>
                                            <input placeholder="Document Type" name="docType" className="form-control"
                                                value={this.state.docType} onChange={this.changeDocumentTypeHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Company Id: </label>
                                            <input placeholder="Company Id" name="companyId" className="form-control"
                                                value={this.state.companyId} onChange={this.changeCompanyIdHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Date: </label>
                                            <input placeholder="Date" name="date" className="form-control"
                                                value={this.state.date} onChange={this.changeDateHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Document Id: </label>
                                            <input placeholder="Document Id" name="docId" className="form-control"
                                                   value={this.state.docId} onChange={this.changeDocumentIdHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Sign: </label>
                                            <input placeholder="Sign" name="sign" className="form-control"
                                                   value={this.state.sign} onChange={this.changeSignHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Amount: </label>
                                            <input placeholder="Amount" name="amount" className="form-control"
                                                   value={this.state.amount} onChange={this.changeAmountHandler}/>
                                        </div>


                                        <button className="btn btn-success" onClick={this.updateDocument}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateFileComponent
