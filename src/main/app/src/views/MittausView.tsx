import React from 'react';

import { observer } from 'mobx-react';

import MittausForm from './MittausForm';
import Container from 'react-bootstrap/Container';


const MittausView = observer(() => {
  return (
    <Container fluid>
      <MittausForm />
    </Container>
  )
})
export default MittausView;
