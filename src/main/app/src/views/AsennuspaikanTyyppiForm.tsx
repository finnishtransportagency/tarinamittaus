import React from "react";

import { Form, Col, Row } from "react-bootstrap";
import { StackedText } from "../components/StackedText";
import { CustomSelect } from "../components/CustomSelect";

const AsennuspaikanTyyppiForm = ({ namespace }: { namespace: string }) => {
  return (
    <div key={namespace}>
      <Form.Group as={Row}>
        <Col sm="6">
          <CustomSelect
            label="Anturin sijoituspaikka"
            name={`${namespace}.selite`}
            readOnly={false}
          />
        </Col>
        <Col sm="6">
          <StackedText
            label="Anturin sijoituspaikan lisätiedot"
            name={`${namespace}.lisatiedot`}
            readOnly={false}
          />
        </Col>
      </Form.Group>
    </div>
  );
};

export default AsennuspaikanTyyppiForm;
