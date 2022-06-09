import { RouteProps } from 'react-router-dom';
import MittausListView from './views/MittausListView';
import MittausView from './views/MittausView';


const routes: RouteProps[] = [
  {
    path: '/',
    exact: true,
    component: MittausListView,
  },
  {
    path: '/mittaus',
    exact: true,
    component: MittausView,
  },
  {
    path: '/mittaus/:id',
    exact: true,
    component: MittausView,
  },
  {
    path: '/mittauslista',
    exact: true,
    component: MittausListView,
  },
];

export default routes;
