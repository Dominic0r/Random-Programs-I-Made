let gameState={
	loyalists:0,
	militarists:0,
	oligarchs:0,
	cojuangco:0,
	enrile:0,
	reformists:0,
	
	polPower:0,
	
	primeMinister:"Morato",
	
	attemptedOuster:false,
	budgetMaintained:false,
	
	
	oligarchsAppointed: 0,
	
	enrileCoup: false,
}

const story = {
  start: {
	  text:"Welcome to Mabuhay!",
	  choices:[
	  {text: "Start Game", next: "Ques1",}
	  ]
  },
  Ques1: {
	  text: "25 Years. It's been 25 years since your ascension to the Presidency all the way back in 1965. Lots of things have happened since then. Well, the crowd's waiting for you. You get up on stage and begin your speech.",
	  choices:[
	  {text: "Ladies and Gentlemen... It has been a prosperous 25 years...", next:"Ques2",feedback:"Going well so far..."}
	  ]
  },
  
  Ques2:{
	  text: "As you go on about the New Society, Prosperity, Anti-Communism, you suddenly feel a small, sharp pain in your chest. It's enough to make you stop mid-way your speech. The crowd assumes not much of it. You are getting old after all. You feel the pain again. Suddenly it's as if you can't breathe",
	  choices:[
	  {text:"hck-", next:"Ques3", feedback: "Hell of a life"}
	  ]
  },
  
  Ques3:{
	  text: "It's been a few days since the incident. You're in the hospital, your condition is far too delicate to relocate, plus doing such a thing would cause a media storm and possibly a popular uprising should the news break out. The state media's reporting that you're doing well. You doubt anyone believes it. You just collapsed in front of the entire nation. Imelda reassured you everything will be fine before she left but you have your doubts. Slowly, you close your eyes as the monitor beside you beeps on... BEEP.... BEEP.... BEEP............",
	  choices:[
	  {text: "...BEEEEEEEEEEEEEEEP", next:"Ques4", feedback: "End of an era", effect: ()=>{ setCharacterImage('https://media.discordapp.net/attachments/1018463369503715409/1402978699422208060/image.png?ex=6895e13c&is=68948fbc&hm=2a0753c74a4288685834924772fce42895f8272d2ea155d0e61a1e1cd0f967ca&=&format=webp&quality=lossless');}}
	  ]
  },
  
  Ques4:{
	  text: "What happened... what the hell happened? Just a week ago things were going fine. For 25 years things have been going fine. Now you've just walked into President Marcos' holding room and all you can hear is a long beeping sound. Now instead of a courtesy visit, you're left with a choice...",
	  choices:[
	  {text: "Contact state media and order them to prepare an announcement for his death immediately. The nation must know...", next: "Ques6", feedback: "Within a few days, his obituary is being aired by every single station, and after that several patriotic songs."},
	  
	  {text: "Delay it. I need to inform some close associates about this first...", feedback:"You order the METROCOM guards to heighten security. No one gets in and no one gets out. You then board your car and head straight to your office", next: "Ques5"}
	  ]
  },
  
  Ques5: {
	  text: "You sit down in your office, the telephone laid right in front of you. Slowly, you pick it up and dial. A short beep followed by the sound of someone picking it up...",
	  
	  choices:[
	  {text: "Hello, Mrs. Marcos, this is Vice President Arturo Tolentino...", feedback: "She's grief stricken as expected", next: "Ques6", effect: ()=> gameState.loyalists++},
	  {text: "Danding, it's Arturo...", feedback: "He's quiet for a bit but he soon responds with a simple 'I see' ", next:"Ques6", effect: ()=> gameState.oligarchs++}
	  ]
  },
  
  Ques6:{
	  text: "You sit there in your office, alone once more. You look at the calendar and think about how long the national period of mourning will be.",
	  
	  choices:[
	  {text: "Of course, we will give a week of national mourning, and have state media only broadcast propaganda for the same time period. It's befitting of a man of his excellence", feedback: "As you wish, an entire week is dedicated to the deceased Eternal Leader. Behind the scenes you prepare to take the reins as the country's next chief executive", next: "Ques7", effect: ()=> gameState.loyalists++},
	  
	  {text: "A week should suffice. His death is a national tragedy but there's no reason to completely shut down the nation.", feedback: "For an entire week, the Philippine nation mourns the loss of its President, its Helmsman.", next: "Ques7"},
	  
	  {text: "3 days. I respect the man but... we have to distance ourselves from him somewhat. Plus, it'll be a hassle for Danding and his friends for operations to cease for an entire week...", feedback: "Cojuangco and cohorts begin planning their moves over the course of what is essentially just a slightly longer weekend", next:"Ques7", effect:()=> gameState.Oligarchs++}
	  ]
  },
  
  
  
  
};

let currentScene = 'start';

let sourceimgLeader = "https://media.discordapp.net/attachments/1018463369503715409/1402638666567909437/image.png?ex=68954d4d&is=6893fbcd&hm=b5abf0d3f6d06b9b619c87e6092d17a03d9bf9d9fd812376a6327daf91374073&=&format=webp&quality=lossless";


function setCharacterImage(src) {
  const img = document.getElementById('character-image');
  sourceimgLeader = src;
  img.style.display = 'block';
}




function updateDebugPanel() {
  const debug = document.getElementById('debug-panel');
  debug.innerText = `
Loyalists:           ${gameState.loyalists}
Militarists:         ${gameState.militarists}
Oligarchs:           ${gameState.oligarchs}
Cojuangco:           ${gameState.cojuangco}
Enrile:              ${gameState.enrile}
Reformists:          ${gameState.reformists}

Political Power:     ${gameState.polPower}
Prime Minister:      ${gameState.primeMinister}

Attempted Ouster:    ${gameState.attemptedOuster}
Budget Maintained:   ${gameState.budgetMaintained}

Oligarchs Appointed: ${gameState.oligarchsAppointed}
Enrile Coup:         ${gameState.enrileCoup}
`.trim();
}






function renderScene() {
	updateDebugPanel();
  const scene = story[currentScene];
  const sceneText = typeof scene.text === 'function' ? scene.text() : scene.text;
  document.getElementById('text').innerText = sceneText;
  
  
	document.getElementById('character-image').src = sourceimgLeader;
	document.getElementById('character-image').style.display = 'block';
  

  const choicesDiv = document.getElementById('choices');
  choicesDiv.innerHTML = '';

  const sceneChoices = typeof scene.choices === 'function' ? scene.choices() : scene.choices;

  sceneChoices.forEach(choice => {
    const btn = document.createElement('div');
    btn.classList.add('choice');
    btn.innerText = choice.text;
    btn.onclick = () => {
      if (choice.effect) choice.effect();

      if (choice.feedback) {
        // Show the feedback box
        document.getElementById('feedback-text').innerText = choice.feedback;
        document.getElementById('feedback-box').classList.add('show');
		
		document.getElementById('choices').style.display = 'none';
		
        // Wait for player to click "Continue"
        document.getElementById('continue-btn').onclick = () => {
          document.getElementById('feedback-box').classList.remove('show');
		  document.getElementById('choices').style.display = 'block';
          currentScene = choice.next;
          renderScene();
        };
      } else {
        // No feedback, just go to next scene
        currentScene = choice.next;
        renderScene();
      }
    };
    choicesDiv.appendChild(btn);
  });
  
  
}


renderScene();
