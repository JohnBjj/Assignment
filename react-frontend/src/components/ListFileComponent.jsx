import React, { Component,} from 'react'
import FileService from "../services/FileService";
import LoadFileComponent from "./LoadFileComponent";

class ListFileComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
                documents: [],
                file: '',
                amount: ''

        }
        this.editFile = this.editFile.bind(this);
        this.deleteFile = this.deleteFile.bind(this);

    }

    calculateAmout = () => {

        let amount = 0;
        this.state.documents.forEach( document => {

            amount = amount + document.amount
        })
        this.setState({
            amount:amount
        });

    }


    deleteFile(id){
        FileService.deleteFile(id).then( res => {
            this.setState({documents: this.state.documents.filter(document => document.id !== id)});
            this.calculateAmout()

        });
    }

    editFile(id){
        this.props.history.push(`/update-file/${id}`);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
    }




    componentDidMount(){
        // window.location.reload();

        FileService.getFiles().then((res) => {
            this.setState({ documents: res.data});
            this.calculateAmout()
        });

    }


    render() {
        return (
            <div>
                 <h2 className="text-center">Document List</h2>

                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Document Type</th>
                                    <th> Company Id</th>
                                    <th> Date </th>
                                    <th> Document Id</th>
                                    <th> Sign </th>
                                    <th> Amount </th>
                                    <th> Actions </th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.documents.map(
                                        document =>
                                        <tr key = {document.id}>
                                             <td> {document.docType} </td>
                                             <td> {document.companyId}</td>
                                             <td> {document.date}</td>
                                             <td> {document.docId}</td>
                                             <td> {document.sign}</td>
                                             <td> {document.amount}</td>
                                             <td>
                                                 <button onClick={ () => this.editFile(document.id)}
                                                         className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}}
                                                         onClick={ () => this.deleteFile(document.id)}
                                                         className="btn btn-danger"> Delete </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

                 <div className="container">
                     <div className="row">
                         <div className="col-lg-9"></div>
                         <div className="col-lg-3"><p>TOTAL AMOUNT {this.state.amount}</p>
                         </div>
                     </div>
                 </div>

                 <br></br>

                 <LoadFileComponent />

            </div>
        )
    }
}

export default ListFileComponent
