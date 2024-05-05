'use client';
import {useState } from "react";
import Image from "next/image";

import {
    Button,
    Dialog,
    DialogHeader,
    DialogBody,
    DialogFooter,
  } from "@material-tailwind/react";
   
  export default function DialogHow() {
    const [open, setOpen] = useState(false);
   
    const handleOpen = () => setOpen(!open);
   
    return (
      <>
        <Button onClick={handleOpen} variant="gradient" size="sm" color="red">
          <Image src="/question.svg" width={30} height={30} />
        </Button>
        <Dialog
          open={open}
          handler={handleOpen}
          animate={{
            mount: { scale: 1, y: 0 },
            unmount: { scale: 0.9, y: -100 },
          }}
        >
          <DialogHeader>World Ladder Solver.</DialogHeader>
          <DialogBody>
          Word ladder solution is a tool designed to find the shortest sequence 
          of words that connects two given words by changing one letter at a time, 
          with each intermediate word being a valid English word. It's a fun and 
          educational way to explore vocabulary and language connections.
          </DialogBody>
          <DialogBody>
          <ul>
            <li>
              1. Enter two words in the input fields provided.
            </li>
            <li>
            2. Select algorithm to use for the search.
            </li>
            <li>
            3. Click on the Start button to find the path between the two words.
            </li>
          </ul>
          </DialogBody>
          <DialogFooter>
            <Button variant="gradient" color="green" onClick={handleOpen}>
              <span>Understood</span>
            </Button>
          </DialogFooter>
        </Dialog>
      </>
    );
  }