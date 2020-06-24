import React from 'react';
import { createStore } from "redux";
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import { Container, Row } from 'react-bootstrap';
import PerguntaPage from './PerguntaPage';
import RespostaPage from './RespostaPage';
import CredenciamentoHeader from '../component/CredenciamentoHeader';
import Crendenciais from '../reducer/Credenciais';
import '../style/RootPage.css';


export default function RootPage() {

    const store = createStore(Crendenciais);

    return (
        <Container fluid className="Root">
            <CredenciamentoHeader store={ store } />
            <Row>
                <Router>
                    <Switch>
                        <Route path="/" exact>
                            <PerguntaPage store={ store } />
                        </Route>
                        <Route path="/perguntas/:id" children={<RespostaPage store={ store } />} />
                    </Switch>
                </Router>
            </Row>
        </Container>
    )
}
