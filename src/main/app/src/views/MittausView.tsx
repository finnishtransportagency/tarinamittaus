import React from 'react';

import { observer } from 'mobx-react';
import MittausStore from '../stores/MittausStore';

import MittausForm from './MittausForm';
import Container from 'react-bootstrap/Container';


const MittausView = observer(({ mittaus }: { mittaus: MittausStore }) => {
  return (
    <Container fluid>
      <MittausForm mittaus={mittaus} />
    </Container>
  )
})
export default MittausView;
