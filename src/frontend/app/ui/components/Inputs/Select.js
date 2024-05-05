'use client';

import { Select, Option } from "@material-tailwind/react";
 
export function SelectMethod({handleChange, value}) {
  return (
    <div className="w-72">
      <Select
        label="Select Algorithm"
        color="black"
        animate={{
          mount: { y: 0 },
          unmount: { y: 25 },
        }}
        onChange={val => {handleChange(val)}}
        value={value}
      >
        <Option value="ucs">Uniform-Cost Search</Option>
        <Option value="gbfs">Greedy Best First Search</Option>
        <Option value="astar">A Star Algorithm</Option>
      </Select>
    </div>
  );
}
