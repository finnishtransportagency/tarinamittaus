import React from "react";
import Table from 'react-bootstrap/Table'
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import Pagination from 'react-bootstrap/Pagination';
import axios from 'axios';

import IMittaus from "../types/interfaces/mittaus.interface";
import { fullRestURL } from "../App";

const baseUrl = fullRestURL();

const itemsPerPage = 10;

const MittausListView = () => {
    const [mittausData, setMittausData] = React.useState([]);
    const [activePage, setActivePage] = React.useState(0);
    const [nItems, setNItems] = React.useState(0);

    const fetchAndSetData = async () => {
      console.log(`fetching from ${baseUrl}`)
      axios.get(baseUrl)
      .then(response => {
        setMittausData(response.data);
        setNItems(response.data.length);
      })
      .catch(error => {
        console.log(error);
      });

    }

    React.useEffect(() => {
        fetchAndSetData();
    }, []);

    const startIndex = activePage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const nPages = Math.ceil(nItems/itemsPerPage);
    const pageIndexes = Array(nPages).fill(null).map((_, i) => i);
    const displayedPageIndexes = pageIndexes.slice(Math.max(0, activePage - 5), Math.min(nPages, activePage + 6));
    return (
      <>
        <div style={{'display': 'flex', 'float': 'right', 'padding': '5px 20px'}}>
          <Link to="/mittaus">
            <Button>Uusi</Button>
          </Link>
        </div>
        <Table striped>
          <thead>
            <tr>
              <th>
                Alkuaika - loppuaika
              </th>
              <th>
                Katuosoite
              </th>
              <th>
                Postinumero
              </th>
            </tr>
          </thead>
          <tbody>
            {mittausData ? mittausData.slice(startIndex, endIndex).map((mittaus: IMittaus, idx) => (
              <tr key={idx}>
                <td>
                  <Link to={`/mittaus/${mittaus.kohde_id}`}>{`${mittaus.alkuaika} - ${mittaus.loppuaika}`}</Link>
                </td>
                <td>
                  {mittaus.katuosoite || "ei osoitetta"}
                </td>
                <td>
                  {mittaus.postinumero || "ei postinumeroa"}
                </td>
              </tr>
            )) : "ei mittauksia"}
          </tbody>
        </Table>
        <div style={{'display': 'flex', 'justifyContent': 'center'}}>
          <Pagination>
            <Pagination.First onClick={() => setActivePage(0)}/>
            {displayedPageIndexes.map((i) => (
              <Pagination.Item
                key={i}
                active={activePage === (i)}
                onClick={() => setActivePage(i)}>
                  {i + 1}
              </Pagination.Item>
            ))}
            <Pagination.Last onClick={() => setActivePage(nPages - 1)}/>
          </Pagination>
        </div>
      </>
    );
}

export default MittausListView;
