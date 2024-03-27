import React from "react";
import { ButtonContainer } from "./styles";

const Button = ({label, onClick}) => {
  return (
    <ButtonContainer onClick={onClick}>
      <div className="App">
        {label}
      </div>
    </ButtonContainer>
  );
}

export default Button;
