const Team = () => {
  const team = [
    {
      name: "Anton Jayakody",
      position: "Master In Charge",
      coordinates: "absolute bottom-0 left-0",
    },
    {
      name: "Mandinu Balasooriya",
      position: "President 24/25",
      coordinates: "absolute top-0 left-1/3",
    },
    {
      name: "Yasiru Dharmathilaka",
      position: "Vice-President 24/25",
      coordinates: "absolute bottom-0 right-0",
    },
    {
      name: "Akash De Silva",
      position: "Buildathon Lead 24/25",
      coordinates: "absolute top-1/2 left-1/4",
    },
    {
      name: "Ometh Abeyrathne",
      position: "Algothan Lead 24/25",
      coordinates: "absolute top-1/4 right-1/4",
    },
  ];
  return (
    <div
      id="team"
      className="relative w-full flex flex-col items-start px-4 py-20 bg-white"
    >
      <div className="text-black text-8xl mb-8">Team</div>
      <div className="text-black text-4xl mb-2 max-w-3xl">
        Even the best programmers need the best people with the right spirit
        behind it.
      </div>
      <div className="text-black text-4xl mb-2 max-w-3xl">
        We have a team of dreamers and doers just like you, ready to help you
        out.
      </div>
      <div className="text-black text-4xl max-w-3xl">
        Here are the folks leading the charge.
      </div>
      <div className="relative h-120 w-full">
        {team.map((member, index) => (
          <div
            key={index}
            className={`${member.coordinates} flex flex-col justify-end items-start bg-white border-2 border-black h-120 w-100 p-2`}
          >
            <div className="text-4xl">{member.name}</div>
            <div className="text-2xl">{member.position}</div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Team;
