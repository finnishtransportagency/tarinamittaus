import React from "react";
import { RouteProps } from "react-router";
import MittausListView from "./views/MittausListView";
import MittausView from "./views/MittausView";

const routes: RouteProps[] = [
  {
    path: "/",
    element: <MittausListView />,
  },
  {
    path: "/mittaus",
    element: <MittausView />,
  },
  {
    path: "/mittaus/:id",
    element: <MittausView />,
  },
  {
    path: "/mittauslista",
    element: <MittausListView />,
  },
];

export default routes;
