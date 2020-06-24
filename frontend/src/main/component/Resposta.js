import React from 'react'
import { Container, Row, Col } from 'react-bootstrap';
import '../style/Resposta.css';

export default function Resposta({ texto }) {
    return (
        <Container className="px-2 py-3 Resposta">
            <Row>
                <Col>
                    { texto }
                </Col>
            </Row>
        </Container>
    )
}
