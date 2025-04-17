import { render, screen } from "@testing-library/react";
import App from "./App";

describe("main app", () => {
  it("renders main heading", () => {
    render(<App />);
    const linkElement = screen.getByRole("heading", { name: "Tärinämittaus" });
    expect(linkElement).toBeInTheDocument();
  });
});
