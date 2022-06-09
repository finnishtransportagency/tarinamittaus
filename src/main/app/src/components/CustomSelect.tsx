import React from "react";

import { useField } from "formik";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Select from "react-select";
import SeliteTypeEnum from "../types/enums/seliteType.enum";

export const CustomSelect = ({
  label,
  name,
  readOnly,
}: {
  label: string;
  name: string;
  readOnly: boolean;
}) => {
  const [field, meta, { setValue, setTouched }] = useField(name);

  const options = Object.keys(SeliteTypeEnum).map((k) => ({
    value: k,
    label: k,
  }));

  return (
    <Col className={meta.error && "has-error"}>
      <Form.Label htmlFor="select">{label}</Form.Label>
      <Select
        options={options}
        id="select"
        onChange={(option) => {
          setTouched(true);
          setValue(
            option && (option as { value: string; label: string }).value
          );
        }}
        defaultValue={
          options.find((option) => option.value === field.value) || ""
        }
        isDisabled={readOnly}
      />
      {meta.touched && meta.error && (
        <small className="react-form-message react-form-message-error">
          {meta.error}
        </small>
      )}
    </Col>
  );
};
