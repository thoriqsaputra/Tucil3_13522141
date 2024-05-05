'use client'; 

import DialogHow from "./ui/components/howtouse";
import Image from "next/image";
import Result from "./ui/components/Result/Result";
import TextInput from "./ui/components/Inputs/Input";
import { jac } from "./ui/Font";
import { useState } from "react";
import { SelectMethod } from "./ui/components/Inputs/Select";
import { Button } from "@material-tailwind/react";
import { useRef, useEffect } from "react";
import { ToastContainer, toast, Flip, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
 
export default function Home() {
  const [startWord, setStartWord] = useState("");
  const [endWord, setEndWord] = useState("");
  const [algorithm, setAlgorithm] = useState("");
  const [result, setResult] = useState({});
  const [showResult, setShowResult] = useState(false);
  const [showLoading, setShowLoading] = useState(false);
  const resultSectionRef = useRef<HTMLDivElement>(null);
  const [isValidStart, setIsValidStart] = useState(true);
  const [isValidEnd, setIsValidEnd] = useState(true);


  const notify = () => toast.error('Both words must be the same length', {
                            position: "top-center",
                            autoClose: 2000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            theme: "colored",
                            transition: Flip,
                            });

  const emptyfields = () => toast.error('Both fields must be filled', {
                            position: "top-center",
                            autoClose: 2000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            theme: "colored",
                            transition: Bounce,
                            });

  const not_valid = () => toast.error('Both words must be a valid english word', {
                            position: "top-center",
                            autoClose: 2000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            theme: "colored",
                            transition: Flip,
                            });
  
  const no_algorithm = () => toast.error('Please select an algorithm', {
                            position: "top-center",
                            autoClose: 2000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            theme: "colored",
                            transition: Flip,
                            });

  const checkWord = async (e, string) => {
    const word = e.target.value;
    const response = await fetch('http://localhost:8080/validword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(word),
    });
    
    // Assuming response.json() returns a boolean indicating validity
    const isValidWord = await response.json();

    // Update the isValid state with the validity of the word

    if (string === "start") {
      setIsValidStart(isValidWord.valid);
    } else {
      setIsValidEnd(isValidWord.valid);
    }
  } 

  useEffect(() => {
    if (showResult) {
      resultSectionRef.current?.scrollIntoView({ behavior: 'smooth' });
    }
  }
  , [showResult]);

  const handleSubmit = async () => {

    if (startWord === "" || endWord === "") {
      emptyfields();
      return;
    }
    if (startWord.length !== endWord.length) {
      notify();
      return;
    }

    if (algorithm === "") {
      no_algorithm();
      return;
    }

    if (!isValidStart || !isValidEnd) {
      not_valid();
      return;
    }
    
    setShowResult(false);
    setShowLoading(true);
    const data = {
      startWord: startWord,
      endWord: endWord,
      algorithm: algorithm
    }
    console.log(data);
    try {
      const response = await fetch('http://localhost:8080/wlsolver', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
      const result = await response.json();
      setResult(result);
      setShowResult(true);
      setShowLoading(false);
      console.log(result);
    } catch (error) {
      console.error('Error:', error);
    }
    
  }

  const handleAlgorithm = (e) => {
    setAlgorithm(e);
    setShowResult(false);
  }
  
  const handleStartWord = (e) => {
    setStartWord(e.target.value);
    setShowResult(false);
    checkWord(e, "start");
  };
  
  const handleEndWord = (e) => {
    setEndWord(e.target.value);
    setShowResult(false);
    checkWord(e, "end");
  };
  
  return (
    <main>
      <ToastContainer />
      <section className="flex h-screen relative flex-col items-center justify-between">
        <div className="wave_top">
          <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
              <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" className="shape-fill"></path>
              <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" className="shape-fill"></path>
              <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" className="shape-fill"></path>
          </svg>
        </div>
        <section className="pt-[150px] relative">
          <div className="absolute z-0 ml-[-400px] mt-[-50px]">
            <DialogHow />
          </div>
          <div className="bg-gradient-to-b from-indigo-500/20 to-blue-500/40 shadow-lg shadow-blue-500/40 rounded-3xl p-3">
            <h1 className={`${jac.className} text-5xl `}>Word Ladder Solver!!  </h1>
          </div>
          <div className="p-5 bg-gradient-to-tr from-black/40 to-gray-500/20 gap-5 flex flex-col rounded-xl mt-8 ">
            <TextInput label={"Start Word"} placeholder={"commence"} value={startWord} handleChange={handleStartWord} Validity={isValidStart} />
            <TextInput label={"End Word"} placeholder={"conclude"} value={endWord} handleChange={handleEndWord} Validity={isValidEnd} />
          </div>
          <div className="flex flex-row items-center justify-center mt-5 gap-10">
            <div className="p-5 w-80 rounded-lg bg-gradient-to-bl from-red-100 to-cyan-200 flex ">
              <SelectMethod handleChange={handleAlgorithm} value={algorithm} />
            </div>
            <div className="p-[17px] bg-gradient-to-br from-indigo-500/55 to-gray-800/55 rounded-lg">
              <Button color="red" ripple={true} size="lg" onClick={handleSubmit} placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>Start Solving</Button>
            </div>
          </div>
          <div className="flex justify-center mt-5">
            {showLoading &&
            <Image src="/cute-kitty.gif" alt="cute-kitty" width={130} height={130} />
            }
          </div>
        </section>
        <div className="wave_bottom">
            <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" className="shape-fill"></path>
                <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" className="shape-fill"></path>
                <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" className="shape-fill"></path>
            </svg>
        </div>
      </section>
      <div ref={resultSectionRef} >
        {showResult && <Result Result={result} />}
      </div>
    </main>
    
  );
}
