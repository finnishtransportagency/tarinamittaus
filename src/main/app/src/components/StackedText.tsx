import React from "react";
import { useField } from "formik";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";

export const StackedText = ({
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
    <Col className="mb-3">
      <Form.Label htmlFor={name}>{label}</Form.Label>
      <Form.Control
        {...field}
        id={name}
        placeholder=""
        type="text"
        readOnly={readOnly}
        isInvalid={!!meta.error}
      />
      {meta.touched && meta.error && (
        <small className="react-form-message react-form-message-error">
          {meta.error}
        </small>
      )}
    </Col>
  );
};
