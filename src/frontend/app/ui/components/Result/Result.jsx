import Image from 'next/image';
import { racing, jac, kumar } from '../../Font';


export default function Result({Result}) {
  const wordlist = Result.processedList;

  return (
    <main className='min-h-screen relative bg-gradient-to-b from-[#1B1A55] to-[#070F2B]'>
      <div className="absolute z-0">
        <Image 
          src="/top.svg" 
          alt="curve"
          width={1440}
          height={100}
        />
      </div> 
      <div className="flex flex-col gap-3 items-center justify-center pt-[200px] z-10 pb-10">
        <h1 className={`${racing.className} text-4xl `}>Ladder has been found!</h1>
        <h2 className={`text-2xl ${racing.className}`}>Took {Result.processingTimeMillis} {Result.metric} </h2>
        <h2 className={`text-2xl ${racing.className}`}>{Result.visitedWords} words visited</h2>
        {wordlist.length === 0 
        ? 
        <div className='bg-red-300/10 p-8 outline-double rounded-sm flex flex-col justify-center items-center gap-4'>
          <h1 className={`text-2xl text-red ${kumar.className}`}>No ladder found</h1>
          <div className='bg-white/90 rounded-full'>
            <Image
            src='/Fall.gif'
            alt='fall'
            width={200}
            height={200}
            />
          </div>
        </div>
        :
        <div className='bg-red-300/10 p-5 rounded-sm'>
          <ul className={`bg-black/20 rounded-lg p-5 gap-2 ${wordlist.length >= 6 ? "grid grid-cols-6" : "flex flex-row justify-evenly"}`}>
            {wordlist.map((word, index) => (
              <div className='flex flex-row items-center gap-[2px] justify-evenly bg-black/40 rounded-lg'>
                <div className='bg-blue-300/10 ml-1 p-1 px-2 rounded-xl'>
                  {index+1} 
                </div>
                <li key={index} className='text-2xl p-1 bg-gradient-to-tr from-red-800 to-pink-700 rounded-lg'>{word}</li>
              </div>
            ))}
          </ul>
        </div>
        }
      </div>

    </main>
  );
}