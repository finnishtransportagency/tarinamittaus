import React from 'react';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import { Switch, Route, Link, HashRouter } from 'react-router-dom';

import urljoin from "url-join";
import routes from './App.routes';


function App() {
    return (
        <HashRouter>
            <Container fluid>
                <Row>
                    <Col sm={2}>
                        <h2>Tärinämittaus</h2>
                        <ul className="nav nav-pills">
                            <li className="nav-item" key={"mittauslista"}>
                                <Link
                                    to={fullURL("/mittauslista")}
                                    className="nav-link"
                                >
                                    Mittauslista
                                </Link>
                            </li>
                        </ul>
                    </Col>
                    <Col sm={9} style={{ 'top': '50px' }}>
                        <Switch>
                            {routes.map((route, i) => (
                                <Route key={i} {...route} />
                            ))}
                        </Switch>
                    </Col>

                </Row>
            </Container>
        </HashRouter>
    );
}

export default App;


export function fullURL(...urls: string[]) {
    return urljoin(...urls);
}

export function fullRestURL() {
    // This works
    const BaseRestURL =
        process.env.REACT_APP_BASE_REST_URL || "rest/mittaus/";

    return urljoin(`/${BaseRestURL}`);
}
