import React from "react";
import DatePicker from "react-datepicker";
import { fi } from "date-fns/locale";
import { format, parse, isValid, isDate } from "date-fns";
import { useField } from "formik";

import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";

import "react-datepicker/dist/react-datepicker.css";

// isDate() and isValid() both return true
// for dates with a year of more than 4 digits, but such
// dates lead to a RangeError in format()
const isValidDate = (date: Date | null): date is Date =>
  isDate(date) && isValid(date) && date.getFullYear().toString().length <= 4;

export const FormikCustomDatePicker = ({
  label,
  name,
  readOnly,
}: {
  label: string;
  name: string;
  readOnly: boolean;
}) => {
  const [field, meta, helpers] = useField(name);
  const dateFormat = "yyyy-MM-dd";

  const onChange = React.useCallback(
    (date: Date | null) => {
      helpers.setTouched(true);
      const newDate = isValidDate(date) ? format(date, dateFormat) : null;
      helpers.setValue(newDate);
    },
    [helpers]
  );

  return (
    <Form.Group as={Row} className="mb-3">
      <Form.Label column sm="4" htmlFor={name}>
        {label}
      </Form.Label>
      <Col sm="8">
        <Row style={{ paddingLeft: "15px" }}>
          <DatePicker
            locale={fi}
            dateFormat={dateFormat}
            className={`tk-field form-control ${meta.error && "is-invalid"}`}
            disabled={readOnly}
            selected={
              field.value ? parse(field.value, dateFormat, new Date()) : null
            }
            onChange={onChange}
          />
        </Row>
        <Row style={{ paddingLeft: "15px" }}>
          {meta.touched && meta.error && (
            <small className="react-form-message react-form-message-error">
              {meta.error}
            </small>
          )}
        </Row>
      </Col>
    </Form.Group>
  );
};
