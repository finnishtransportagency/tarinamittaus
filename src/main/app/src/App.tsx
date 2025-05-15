import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import { Route, Link, HashRouter, Routes } from "react-router-dom";

import urljoin from "url-join";
import routes from "./App.routes";

function App() {
  return (
    <HashRouter>
      <Container fluid className="ml-3">
        <Row>
          <Col sm={2} className="mt-3">
            <h2>Tärinämittaus</h2>
            <ul className="nav nav-pills">
              <li className="nav-item" key={"mittauslista"}>
                <Link to="/mittauslista" className="nav-link">
                  Mittauslista
                </Link>
              </li>
            </ul>
          </Col>
          <Col sm={9} style={{ position: "relative", top: "50px" }}>
            <Routes>
              {routes.map((route, i) => (
                <Route key={i} {...route} />
              ))}
            </Routes>
          </Col>
        </Row>
      </Container>
    </HashRouter>
  );
}

export default App;

export function fullRestURL() {
  // This works
  const BaseRestURL = process.env.REACT_APP_BASE_REST_URL || "rest/mittaus/";

  return urljoin(`/${BaseRestURL}`);
}
