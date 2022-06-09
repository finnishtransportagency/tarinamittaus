import React from 'react';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import { Switch, Route, Link, HashRouter } from 'react-router-dom';

import './App.css';
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

// This comes as undefined for some reason
// const BaseRestURL =
//     process.env.REACT_APP_BASE_REST_URL || "tarinamittaus/mittaus";

export function fullURL(...urls: string[]) {
    // NB: user with browserhistory
    // return urljoin(`/${BaseURL}`, ...urls);
    return urljoin(...urls);
}

export function fullRestURL(...urls: string[]) {
    // This works
    const BaseRestURL =
        process.env.REACT_APP_BASE_REST_URL || "tarinamittaus/rest/mittaus/";

    //--- degugging ---
    const urlstring = urljoin(`/${BaseRestURL}`, ...urls);
    console.log(`urlstring: ${urlstring}, baserestURL ${BaseRestURL}`);
    //---
    return urljoin(`/${BaseRestURL}`, ...urls);
}
