import React from "react";

import { useField } from "formik";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";

export const CustomSensorNumber = ({
  label,
  name,
  readOnly,
}: {
  label: string;
  name: string;
  readOnly: boolean;
}) => {
  const [field, meta] = useField(name);

  return (
    <Col sm="4" className={meta.error && "has-error"}>
      <Form.Label htmlFor={name}>{label}</Form.Label>
      <Form.Control {...field} id={name} type="number" readOnly={readOnly} />
      {meta.touched && meta.error && (
        <small className="react-form-message react-form-message-error">
          {meta.error}
        </small>
      )}
    </Col>
  );
};
