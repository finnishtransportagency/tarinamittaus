import React from "react";
import Table from "react-bootstrap/Table";
import { Button, FormControl } from "react-bootstrap";
import { Link } from "react-router-dom";
import Pagination from "react-bootstrap/Pagination";

import IMittaus from "../types/interfaces/mittaus.interface";
import { fullRestURL } from "../App";
import { getList } from "../api";

const baseUrl = fullRestURL();

const itemsPerPage = 10;

const MittausListView: React.FC = (): JSX.Element => {
  const [mittausData, setMittausData] = React.useState<IMittaus[]>([]);
  const [activePage, setActivePage] = React.useState<number>(0);
  const [sortOrder, setSortOrder] = React.useState<"asc" | "desc">("asc");

  const [sortColumn, setSortColumn] = React.useState<"kohde_id" | "katuosoite">(
    "kohde_id"
  );
  const [searchText, setSearchText] = React.useState<string>("");

  const fetchAndSetData = async (): Promise<void> => {
    console.log(`fetching from ${baseUrl}`);
    getList()
      .then((data: IMittaus[]) => {
        setMittausData(data);
      })
      .catch((error: any) => {
        console.log(error);
      });
  };

  React.useEffect(() => {
    fetchAndSetData();
  }, []);

  const handleSort = (column: "kohde_id" | "katuosoite"): void => {
    const sortedData = [...mittausData].sort((a, b) => {
      if (sortOrder === "asc") {
        return a[column] < b[column] ? 1 : -1;
      } else {
        return a[column] > b[column] ? 1 : -1;
      }
    });
    setMittausData(sortedData);
    setSortOrder(sortOrder === "asc" ? "desc" : "asc");
    setSortColumn(column);
  };

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>): void => {
    setSearchText(event.target.value);
  };

  const filteredData = mittausData.filter(
    (mittaus) =>
      mittaus.kohde_id.toString().includes(searchText) ||
      (mittaus.katuosoite &&
        mittaus.katuosoite.toLowerCase().includes(searchText.toLowerCase()))
  );

  const startIndex = activePage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const nPages = Math.ceil(filteredData.length / itemsPerPage);
  const pageIndexes = Array(nPages)
    .fill(null)
    .map((_, i) => i);
  const displayedPageIndexes = pageIndexes.slice(
    Math.max(0, activePage - 5),
    Math.min(nPages, activePage + 6)
  );

  return (
    <>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          paddingBottom: "5px",
          paddingTop: "20px",
        }}
      >
        <FormControl
          type="text"
          placeholder="Etsi Kohdetunnus tai Katuosoite..."
          value={searchText}
          onChange={handleSearch}
          style={{ width: "300px" }}
        />
        <Link to="/mittaus">
          <Button>Uusi</Button>
        </Link>
      </div>
      <Table striped>
        <thead>
          <tr>
            <th
              onClick={() => handleSort("kohde_id")}
              style={{ cursor: "pointer" }}
            >
              Kohdetunnus{" "}
              {sortColumn === "kohde_id" && (sortOrder === "asc" ? "▲" : "▼")}
            </th>
            <th>Alkuaika - loppuaika</th>
            <th
              onClick={() => handleSort("katuosoite")}
              style={{ cursor: "pointer" }}
            >
              Katuosoite{" "}
              {sortColumn === "katuosoite" && (sortOrder === "asc" ? "▲" : "▼")}
            </th>
            <th>Postinumero</th>
          </tr>
        </thead>
        <tbody>
          {filteredData
            .slice(startIndex, endIndex)
            .map((mittaus: IMittaus, idx: number) => (
              <tr key={idx}>
                <td>
                  <Link to={`/mittaus/${mittaus.kohde_id}`}>
                    {mittaus.kohde_id}
                  </Link>
                </td>
                <td>{`${mittaus.alkuaika} - ${mittaus.loppuaika}`}</td>
                <td>{mittaus.katuosoite || "ei osoitetta"}</td>
                <td>{mittaus.postinumero || "ei postinumeroa"}</td>
              </tr>
            ))}
        </tbody>
      </Table>
      <div style={{ display: "flex", justifyContent: "center" }}>
        <Pagination>
          <Pagination.First onClick={() => setActivePage(0)} />
          {displayedPageIndexes.map((i) => (
            <Pagination.Item
              key={i}
              active={activePage === i}
              onClick={() => setActivePage(i)}
            >
              {i + 1}
            </Pagination.Item>
          ))}
          <Pagination.Last onClick={() => setActivePage(nPages - 1)} />
        </Pagination>
      </div>
    </>
  );
};

export default MittausListView;
