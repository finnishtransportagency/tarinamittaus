import React from "react";
import { useField } from "formik";
import Row from 'react-bootstrap/Row';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export const CustomText = ({ label, name, readOnly }: { label: string, name: string, readOnly: boolean }) => {
    const [field, meta] = useField(name);

    return (
        <Form.Group as={Row} className={meta.error && "has-error"}>
            <Form.Label column sm="4" htmlFor={name}>{label}</Form.Label>
            <Col sm="8">
                <Form.Control
                    {...field}
                    id={name}
                    placeholder=""
                    type="text"
                    readOnly={readOnly}
                />
            {meta.touched && meta.error &&
                <small className="react-form-message react-form-message-error">
                    {meta.error}
                </small>}
            </Col>
        </Form.Group>
    );
};
